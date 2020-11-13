# 20201113

127.0.0.1:6379> XADD mystream * hello world
"1605285550847-0"
127.0.0.1:6379> XGROUP CREATE mystream group-1
(error) ERR Unknown subcommand or wrong number of arguments for 'CREATE'. Try XGROUP HELP.
127.0.0.1:6379> XGROUP CREATE mystream group-1 $
OK
127.0.0.1:6379> XADD mystream * name KevinBlandy
"1605285655299-0"
127.0.0.1:6379> XADD mystream * name KevinBlandy
"1605285720195-0"
127.0.0.1:6379> XADD mystream * name KevinBlandy
"1605286249270-0"
127.0.0.1:6379> XADD mystream * name KevinBlandy
"1605286477669-0"
127.0.0.1:6379>
