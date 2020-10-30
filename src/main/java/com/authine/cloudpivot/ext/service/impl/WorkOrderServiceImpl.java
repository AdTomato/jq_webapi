package com.authine.cloudpivot.ext.service.impl;

import com.authine.cloudpivot.engine.api.exceptions.ServiceException;
import com.authine.cloudpivot.ext.entity.WorkOrder;
import com.authine.cloudpivot.ext.entity.WorkOrderQueryCondition;
import com.authine.cloudpivot.ext.entity.WorkflowStatus;
import com.authine.cloudpivot.ext.mapper.WorkOrderMapper;
import com.authine.cloudpivot.ext.service.INode;
import com.authine.cloudpivot.ext.service.WorkOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/14 10:41
 */
@Slf4j
@Service
public class WorkOrderServiceImpl implements WorkOrderService {

    @Resource
    private WorkOrderMapper workOrderMapper;

    @Override
    public List<WorkOrder> getWorkOrderByCreator(WorkOrderQueryCondition condition) {
        Set<WorkOrder> results = new LinkedHashSet<>();
        List<WorkOrder> workOrders = workOrderMapper.getWorkOrderByCreator(condition);
        recursiveChildren(results, workOrders, condition.getTreeLevel());
        return this.treeList(new ArrayList<>(results));
    }

    /**
     * 递归查询子工单
     *
     * @param collection  我创建的工单 + 子工单
     * @param createdList 我创建的工单
     * @param level       子工单显示层级
     */
    private void recursiveChildren(Collection<WorkOrder> collection, List<WorkOrder> createdList, int level) {
        Optional.of(createdList).ifPresent(list -> {
            collection.addAll(list);
            list.forEach(workOrder -> {
                int cLevel = workOrder.getLevel();
                if (cLevel < level || level == -1) {
                    String id = workOrder.getId();
                    List<WorkOrder> childrenList = workOrderMapper.getChildrenWorkOrderById(id);
                    Optional.of(childrenList).ifPresent(children -> {
                        children.forEach(item -> item.setLevel(cLevel + 1));
                        recursiveChildren(collection, childrenList, level);
                    });
                }
            });
        });
    }

    @Override
    public List<WorkOrder> getWorkOrderByRecipient(WorkOrderQueryCondition condition) {
        List<WorkOrder> workOrders = workOrderMapper.getWorkOrderByRecipient(condition);
        return this.treeList(workOrders);
    }

    @Override
    public List<WorkOrder> treeList(List<WorkOrder> list) {
        return this.list2Tree(list, WorkOrder::setChildren,
                (Predicate<WorkOrder>) workOrder -> workOrder.getLevel() == 0);
    }

    @Override
    public WorkOrderQueryCondition getQuery(@NonNull String userId, String title, String urgencyDegree,
                                            String overdue, String status, String pageNumber, String pageSize,
                                            String treeLevel)
            throws ServiceException {
        WorkOrderQueryCondition result = new WorkOrderQueryCondition();
        result.setUserId(userId);
        if (!StringUtils.isEmpty(title)) {
            result.setTitle(title);
        }
        if (!StringUtils.isEmpty(urgencyDegree)) {
            result.setUrgencyDegree(urgencyDegree);
        }

        if (!StringUtils.isEmpty(overdue)) {
            result.setOverdue(Boolean.parseBoolean(overdue));
        }

        if (!StringUtils.isEmpty(status)) {
            try {
                result.setStatus(WorkflowStatus.valueOf(status.toUpperCase()));
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage(), e);
                throw new ServiceException(-1, "订单状态参数错误");
            }
        }

        if (!StringUtils.isEmpty(pageNumber)) {
            result.setPageNumber(Integer.parseInt(pageNumber));
        }

        if (!StringUtils.isEmpty(pageSize)) {
            result.setPageSize(Integer.parseInt(pageSize));
        }

        if (!StringUtils.isEmpty(treeLevel)) {
            result.setTreeLevel(Integer.parseInt(treeLevel));
        }

        return result;
    }

    /**
     * 集合转为树形结构,返回指定节点集合
     *
     * @param dataList      需要转换的集合
     * @param childConsumer 设置子节点回调
     * @param nodePredicate 节点判断
     * @param <N>           树节点类型
     * @return 树形结构集合
     */
    private <N extends WorkOrder> List<N> list2Tree(Collection<N> dataList,
                                                    BiConsumer<N, List<N>> childConsumer,
                                                    Predicate<N> nodePredicate) {
        return list2Tree(dataList, childConsumer, (Function<INode<N, String>, Predicate<N>>) predicate -> nodePredicate);
    }

    /**
     * 列表结构转为树结构,并返回根节点集合
     *
     * @param dataList          数据集合
     * @param childConsumer     子节点消费接口,用于设置子节点
     * @param predicateFunction 节点判断函数,传入helper,获取一个判断是否为指定节点的函数
     * @param <N>               元素类型
     * @return 根节点集合
     */
    private <N extends WorkOrder> List<N> list2Tree(final Collection<N> dataList,
                                                    final BiConsumer<N, List<N>> childConsumer,
                                                    final Function<INode<N, String>, Predicate<N>> predicateFunction) {
        Objects.requireNonNull(dataList, "source list can not be null");
        Objects.requireNonNull(childConsumer, "child consumer can not be null");
        Objects.requireNonNull(predicateFunction, "root predicate function can not be null");

        Supplier<Stream<N>> streamSupplier = () -> dataList.size() < 1000 ? dataList.stream() : dataList.parallelStream();
        /*id,node,构建缓存*/
        Map<String, N> cache = new HashMap<>(0);
        /*parentId,children*/
        Map<String, List<N>> treeCache = streamSupplier.get()
                .peek(node -> {
                    if (node.getParentId() == null) {
                        node.setParentId(ROOT_NODE);
                    }
                    cache.put(node.getId(), node);
                })
                .collect(Collectors.groupingBy(WorkOrder::getParentId));

        Predicate<N> nodePredicate = predicateFunction.apply(new INode<N, String>() {
            @Override
            public List<N> getChildren(String parentId) {
                return treeCache.get(parentId);
            }

            @Override
            public N getNode(String id) {
                return cache.get(id);
            }

        });

        return streamSupplier.get()
                /* 设置每个节点的子节点 */
                .peek(
                        node ->
                                childConsumer.accept(node, treeCache.get(node.getId()))
                )
                /* 获取指定节点 */
                .filter(nodePredicate)
                .collect(Collectors.toList());
    }

}
