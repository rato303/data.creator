SELECT
  UT.TABLE_NAME
FROM
  USER_TABLES UT
INNER JOIN
  USER_CONSTRAINTS UCP
ON
  UT.TABLE_NAME = UCP.TABLE_NAME
AND
  UCP.CONSTRAINT_TYPE = 'P'
LEFT JOIN
  USER_CONSTRAINTS UCR
ON
  UT.TABLE_NAME = UCR.TABLE_NAME
AND
  UCR.CONSTRAINT_TYPE='R'
START WITH
  UT.TABLE_NAME = /* tableName */'T_SHOKYAKU_KIHON'
CONNECT BY
  PRIOR UCP.CONSTRAINT_NAME = UCR.R_CONSTRAINT_NAME
ORDER BY
  LEVEL DESC;