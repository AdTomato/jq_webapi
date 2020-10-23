create or replace view view_work_order as
/* 跨部门工单 */
SELECT a.id                                                                  as id,             -- 工单ID
       a.kbm                                                                 as parentId,       -- 父工单ID
       a.title                                                               as title,          -- 标题摘要
       a.sequenceStatus                                                      as status,         -- 工单状态
       1                                                                     as transDepartment,-- 是否跨部门工单
       a.creater                                                             as creator,        -- 创建人
       a.createdTime                                                         as createdTime,    -- 创建时间
       if(json_valid(b.relaDept), json_extract(b.relaDept, '$[*].id'), null) as department,     -- 关联部门
       json_merge(json_extract(a.checker, '$[*].id'),
                  json_extract(a.StaffSelector1601273661757, '$[*].id'))     as approver,       -- 审批人
       json_merge(json_extract(b.person1, '$[*].id'),
                  json_extract(b.person2, '$[*].id'))                        as executor,       -- 执行人
       json_merge(json_extract(a.checker, '$[*].id'),
                  json_extract(a.StaffSelector1601273661757, '$[*].id'),
                  json_extract(b.person1, '$[*].id'),
                  json_extract(b.person2, '$[*].id'))
                                                                             as recipient,      -- 接收人
       c.startTime                                                           as startTime,      -- 开始时间
       c.finishTime                                                          as endTime,        -- 结束时间
       if(ifnull(a.urgent, 'NORMAL') = '', 'NORMAL', upper(a.urgent))        as urgencyDegree,  -- 紧急程度
       a.Dateover                                                            as deadline,       -- 要求完成日期
       c.id                                                                  as workflowId,     -- 流程实例ID
       null                                                                  as workItemId      -- 任务ID
from ii84q_work_flow_k_dept a,
     ii84q_departs b,
     biz_workflow_instance c
where a.id = b.parentId
  and a.workflowInstanceId = c.id
union
/* 内部工单 */
SELECT a.id                                                           as id,              -- 工单ID
       COALESCE(relevanceDeptN, RelevanceKdept)                       as parentId,        -- 父工单ID
       a.title                                                        as title,           -- 标题摘要
       a.sequenceStatus                                               as status,          -- 工单状态
       0                                                              as transDepartment, -- 是否跨部门工单
       a.creater                                                      as creator,         -- 创建人
       a.createdTime                                                  as createdTime,     -- 创建时间
       null                                                           as department,      -- 关联部门
       json_extract(a.checkPerson, '$[*].id')                         as approver,        -- 审批人
       json_extract(b.person, '$[*].id')                              as executor,        -- 执行人
       json_merge(
               if(json_valid(a.checkPerson),
                  json_extract(a.checkPerson, '$[*].id'),
                  json_array()),
               json_extract(b.person, '$[*].id'))                     as recipient,       -- 接收人
       c.startTime                                                    as startTime,       -- 开始时间
       c.finishTime                                                   as endTime,         -- 结束时间
       if(ifnull(a.urgent, 'NORMAL') = '', 'NORMAL', upper(a.urgent)) as urgencyDegree,   -- 紧急程度
       a.Dateover                                                     as deadline,        -- 要求完成日期
       c.id                                                           as workflowId,      -- 流程实例ID
       null                                                           as workItemId       -- 任务ID
from ii84q_workFlowDeptN a,
     ii84q_Sheet1601021060092 b,
     biz_workflow_instance c
where a.id = b.parentId
  and a.workflowInstanceId = c.id;