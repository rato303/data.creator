SELECT
  UTC.TABLE_NAME
  , UTC.COMMENTS
FROM
  USER_TAB_COMMENTS UTC
WHERE
/*%if @isNotBlank(tableName) */
  UTC.TABLE_NAME LIKE /* @prefix(tableName) */'T%' ESCAPE '$'
/*%end*/