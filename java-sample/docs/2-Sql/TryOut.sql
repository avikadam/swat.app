select count(employee_id) from [sits].[employee] 
where first_name = ''
--group by first_name
having count(employee_id) > 1
--order by first_name

