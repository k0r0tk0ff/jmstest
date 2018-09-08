    Test program for understand how to work JMS and Active MQ
    
    -----------------------------------------------------------
    
    
    Use materials from:
    http://onedeveloper.ru/article?id=13
    http://activemq.apache.org/how-should-i-implement-request-response-with-jms.html
    
    http://activemq.apache.org/jms.html
    https://docs.oracle.com/javaee/1.4/tutorial/doc/#wp84181
	
	-----------------------------------------------------------
	
	Задача 1 

	Написать тест, который будет отправлять сообщения в брокер 
	и принимать в разных режимах как сессий (AUTO_ACK, TRANSACTED, etc.),  
	так и сообщений (персистентные и неперсистентные).  

	Оформить итоговый файл с результатами записи и чтения в каждом режиме.  
	Объяснить полученные результаты.  
	
	Предполагаемое время решения 6-10 часов.
	
	----------------------------
		Решение.
	
	Рассматриваем следующие случаи - 
		Неперсистентные 
			Session.SESSION_TRANSACTED  - 31 nanosec (session.commite() required!)
			Session.AUTO_ACKNOWLEDGE    - 16 nanosec 
			Session.DUPS_OK_ACKNOWLEDGE - 16 nanosec 
			Session.CLIENT_ACKNOWLEDGE  - 31 nanosec (message.acknowledge(); required!)
	
		Персистентные 
			Session.SESSION_TRANSACTED  - 78 nanosec (session.commite() and kahaDb required!) 
			Session.AUTO_ACKNOWLEDGE    - 50 nanosec
			Session.DUPS_OK_ACKNOWLEDGE - 47 nanosec 
			Session.CLIENT_ACKNOWLEDGE  - 94 nanosec (message.acknowledge() and kahaDb required!)
	
    -----------------------------------------------------------	
	
2018-09-07 16:11:38:110 INFO  Main:20 - Start application jmstest ..............  
2018-09-07 16:11:38:110 INFO  Main:21 - MODE = Session.SESSION_TRANSACTED  
2018-09-07 16:11:38:110 INFO  Main:24 - Success create sender object........  
2018-09-07 16:11:38:110 INFO  Main:27 - Success create receiver object ........  
2018-09-07 16:11:38:157 INFO  BrokerService:683 - Using Persistence Adapter: MemoryPersistenceAdapter  
2018-09-07 16:11:38:314 INFO  BrokerService:746 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57121-1536325898173-0:1) is starting  
2018-09-07 16:11:38:314 INFO  TransportServerThreadSupport:69 - Listening for connections at: tcp://127.0.0.1:61616  
2018-09-07 16:11:38:314 INFO  TransportConnector:263 - Connector tcp://127.0.0.1:61616 started  
2018-09-07 16:11:38:329 INFO  BrokerService:773 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57121-1536325898173-0:1) started  
2018-09-07 16:11:38:329 INFO  BrokerService:774 - For help or more information please see: http://activemq.apache.org  
2018-09-07 16:11:38:345 INFO  Main:35 - Success start BrokerService ........  
2018-09-07 16:11:38:439 INFO  FailoverTransport:1052 - Successfully connected to tcp://localhost:61616  
2018-09-07 16:11:38:501 INFO  Sender:38 - Message has been sent success. Message contains that - 'Message for send'  
2018-09-07 16:11:38:532 INFO  Receiver:37 - Success received message. Message contains that - 'Message for send'  
2018-09-07 16:11:38:532 INFO  BrokerService:836 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57121-1536325898173-0:1) is shutting down  2018-09-07 16:11:38:532 INFO  TransportConnector:294 - Connector tcp://127.0.0.1:61616 stopped    
2018-09-07 16:11:38:548 INFO  BrokerService:896 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57121-1536325898173-0:1) uptime 0.406 seconds  
2018-09-07 16:11:38:548 INFO  BrokerService:898 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57121-1536325898173-0:1) is shutdown  
2018-09-07 16:11:38:548 INFO  Main:39 - Success stop BrokerService ........  
  
	-----------------------------------------------------------  
	
2018-09-07 16:18:29:950 INFO  Main:22 - Start application jmstest ..............  
2018-09-07 16:18:29:950 INFO  Main:23 - MODE = Session.AUTO_ACKNOWLEDGE  
2018-09-07 16:18:29:950 INFO  Main:26 - Success create sender object........  
2018-09-07 16:18:29:950 INFO  Main:29 - Success create receiver object ........  
2018-09-07 16:18:29:997 INFO  BrokerService:683 - Using Persistence Adapter: MemoryPersistenceAdapter  
2018-09-07 16:18:30:138 INFO  BrokerService:746 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57188-1536326310013-0:1) is starting  
2018-09-07 16:18:30:138 INFO  TransportServerThreadSupport:69 - Listening for connections at: tcp://127.0.0.1:61616  
2018-09-07 16:18:30:138 INFO  TransportConnector:263 - Connector tcp://127.0.0.1:61616 started  
2018-09-07 16:18:30:138 INFO  BrokerService:773 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57188-1536326310013-0:1) started  
2018-09-07 16:18:30:138 INFO  BrokerService:774 - For help or more information please see: http://activemq.apache.org  
2018-09-07 16:18:30:153 INFO  Main:37 - Success start BrokerService ........  
2018-09-07 16:18:30:309 INFO  Sender:41 - Message has been sent success. Message contains that - 'Message for send'  
2018-09-07 16:18:30:325 INFO  Receiver:38 - Success received message. Message contains that - 'Message for send'  
2018-09-07 16:18:30:341 INFO  BrokerService:836 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57188-1536326310013-0:1) is shutting down  2018-09-07 16:18:30:341 INFO  TransportConnector:294 - Connector tcp://127.0.0.1:61616 stopped  
2018-09-07 16:18:30:341 INFO  BrokerService:896 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57188-1536326310013-0:1) uptime 0.344 seconds  
2018-09-07 16:18:30:341 INFO  BrokerService:898 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57188-1536326310013-0:1) is shutdown  
2018-09-07 16:18:30:341 INFO  Main:41 - Success stop BrokerService ........	  
  
	-----------------------------------------------------------  
	
2018-09-07 16:20:27:278 INFO  Main:23 - Start application jmstest ..............  
2018-09-07 16:20:27:278 INFO  Main:24 - MODE = Session.AUTO_ACKNOWLEDGE  
2018-09-07 16:20:27:278 INFO  Main:27 - Success create sender object........  
2018-09-07 16:20:27:278 INFO  Main:30 - Success create receiver object ........  
2018-09-07 16:20:27:325 INFO  BrokerService:683 - Using Persistence Adapter: MemoryPersistenceAdapter  
2018-09-07 16:20:27:465 INFO  BrokerService:746 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57218-1536326427340-0:1) is starting  
2018-09-07 16:20:27:465 INFO  TransportServerThreadSupport:69 - Listening for connections at: tcp://127.0.0.1:61616  
2018-09-07 16:20:27:465 INFO  TransportConnector:263 - Connector tcp://127.0.0.1:61616 started  
2018-09-07 16:20:27:465 INFO  BrokerService:773 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57218-1536326427340-0:1) started  
2018-09-07 16:20:27:465 INFO  BrokerService:774 - For help or more information please see: http://activemq.apache.org  
2018-09-07 16:20:27:497 INFO  Main:38 - Success start BrokerService ........  
2018-09-07 16:20:27:668 INFO  Sender:41 - Message has been sent success. Message contains that - 'Message for send'  
2018-09-07 16:20:27:684 INFO  Receiver:38 - Success received message. Message contains that - 'Message for send'  
2018-09-07 16:20:27:684 INFO  BrokerService:836 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57218-1536326427340-0:1) is shutting down  
2018-09-07 16:20:27:684 INFO  TransportConnector:294 - Connector tcp://127.0.0.1:61616 stopped  
2018-09-07 16:20:27:700 INFO  BrokerService:896 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57218-1536326427340-0:1) uptime 0.375 seconds  
2018-09-07 16:20:27:700 INFO  BrokerService:898 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57218-1536326427340-0:1) is shutdown  
2018-09-07 16:20:27:700 INFO  Main:42 - Success stop BrokerService ........	  
  
	-----------------------------------------------------------  
	
2018-09-07 16:22:38:992 INFO  Main:23 - Start application jmstest ..............  
2018-09-07 16:22:39:008 INFO  Main:24 - MODE = Session.AUTO_ACKNOWLEDGE  
2018-09-07 16:22:39:008 INFO  Main:27 - Success create sender object........  
2018-09-07 16:22:39:008 INFO  Main:30 - Success create receiver object ........  
2018-09-07 16:22:39:039 INFO  BrokerService:683 - Using Persistence Adapter: MemoryPersistenceAdapter  
2018-09-07 16:22:39:180 INFO  BrokerService:746 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57287-1536326559055-0:1) is starting  
2018-09-07 16:22:39:180 INFO  TransportServerThreadSupport:69 - Listening for connections at: tcp://127.0.0.1:61616  
2018-09-07 16:22:39:180 INFO  TransportConnector:263 - Connector tcp://127.0.0.1:61616 started  
2018-09-07 16:22:39:196 INFO  BrokerService:773 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57287-1536326559055-0:1) started  
2018-09-07 16:22:39:196 INFO  BrokerService:774 - For help or more information please see: http://activemq.apache.org  
2018-09-07 16:22:39:211 INFO  Main:38 - Success start BrokerService ........  
2018-09-07 16:22:39:352 INFO  Sender:41 - Message has been sent success. Message contains that - 'Message for send'  
2018-09-07 16:22:39:383 INFO  Receiver:38 - Success received message. Message contains that - 'Message for send'  
2018-09-07 16:22:39:383 INFO  BrokerService:836 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57287-1536326559055-0:1) is shutting down  
2018-09-07 16:22:39:383 INFO  TransportConnector:294 - Connector tcp://127.0.0.1:61616 stopped  
2018-09-07 16:22:39:383 INFO  BrokerService:896 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57287-1536326559055-0:1) uptime 0.344 seconds  
2018-09-07 16:22:39:383 INFO  BrokerService:898 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57287-1536326559055-0:1) is shutdown  
2018-09-07 16:22:39:383 INFO  Main:42 - Success stop BrokerService ........	  
  
	-----------------------------------------------------------  
	----    USE Persistence  (need KAHA DB in pom.xml)  -------    
	-----------------------------------------------------------   
	
2018-09-07 16:42:07:739 INFO  Main:23 - Start application jmstest ..............  
2018-09-07 16:42:07:739 INFO  Main:24 - MODE = Session.SESSION_TRANSACTED  
2018-09-07 16:42:07:739 INFO  Main:27 - Success create sender object........  
2018-09-07 16:42:07:739 INFO  Main:30 - Success create receiver object ........  
true  
2018-09-07 16:42:07:833 INFO  BrokerService:683 - Using Persistence Adapter: KahaDBPersistenceAdapter[E:\Projects\jmstest-origin\jmstest-master\activemq-data\localhost\KahaDB]  
2018-09-07 16:42:07:989 INFO  MessageDatabase:191 - KahaDB is version 6  
2018-09-07 16:42:08:051 INFO  PListStoreImpl:371 - PListStore:[E:\Projects\jmstest-origin\jmstest-master\activemq-data\localhost\tmp_storage] started  
2018-09-07 16:42:08:239 INFO  BrokerService:746 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57635-1536327728067-0:1) is starting  
2018-09-07 16:42:08:270 INFO  TransportServerThreadSupport:69 - Listening for connections at: tcp://127.0.0.1:61616  
2018-09-07 16:42:08:270 INFO  TransportConnector:263 - Connector tcp://127.0.0.1:61616 started  
2018-09-07 16:42:08:270 INFO  BrokerService:773 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57635-1536327728067-0:1) started  
2018-09-07 16:42:08:270 INFO  BrokerService:774 - For help or more information please see: http://activemq.apache.org  
2018-09-07 16:42:08:270 INFO  Main:39 - Success start BrokerService ........  
2018-09-07 16:42:08:348 INFO  Sender:40 - Message has been sent success. Message contains that - 'Message for send'  
2018-09-07 16:42:08:426 INFO  Receiver:38 - Success received message. Message contains that - 'Message for send'  
2018-09-07 16:42:08:551 INFO  BrokerService:836 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57635-1536327728067-0:1) is shutting down  
2018-09-07 16:42:08:551 INFO  TransportConnector:294 - Connector tcp://127.0.0.1:61616 stopped  
2018-09-07 16:42:08:567 INFO  PListStoreImpl:396 - PListStore:[E:\Projects\jmstest-origin\jmstest-master\activemq-data\localhost\tmp_storage] stopped  
2018-09-07 16:42:08:567 INFO  KahaDBStore:264 - Stopping async queue tasks  
2018-09-07 16:42:08:567 INFO  KahaDBStore:278 - Stopping async topic tasks  
2018-09-07 16:42:08:567 INFO  KahaDBStore:306 - Stopped KahaDB  
2018-09-07 16:42:08:786 INFO  BrokerService:896 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57635-1536327728067-0:1) uptime 1.000 seconds  
2018-09-07 16:42:08:786 INFO  BrokerService:898 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57635-1536327728067-0:1) is shutdown  
2018-09-07 16:42:08:786 INFO  Main:43 - Success stop BrokerService ........	  
  
	-----------------------------------------------------------  
	  
	2018-09-07 16:44:50:207 INFO  Main:23 - Start application jmstest ..............  
2018-09-07 16:44:50:207 INFO  Main:24 - MODE = Session.AUTO_ACKNOWLEDGE  
2018-09-07 16:44:50:207 INFO  Main:27 - Success create sender object........  
2018-09-07 16:44:50:207 INFO  Main:30 - Success create receiver object ........  
true  
2018-09-07 16:44:50:316 INFO  BrokerService:683 - Using Persistence Adapter: KahaDBPersistenceAdapter[E:\Projects\jmstest-origin\jmstest-master\activemq-data\localhost\KahaDB]  
2018-09-07 16:44:50:426 INFO  MessageDatabase:191 - KahaDB is version 6  
2018-09-07 16:44:50:488 INFO  PListStoreImpl:371 - PListStore:[E:\Projects\jmstest-origin\jmstest-master\activemq-data\localhost\tmp_storage] started  
2018-09-07 16:44:50:629 INFO  BrokerService:746 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57665-1536327890504-0:1) is starting  
2018-09-07 16:44:50:660 INFO  TransportServerThreadSupport:69 - Listening for connections at: tcp://127.0.0.1:61616  
2018-09-07 16:44:50:660 INFO  TransportConnector:263 - Connector tcp://127.0.0.1:61616 started  
2018-09-07 16:44:50:676 INFO  BrokerService:773 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57665-1536327890504-0:1) started  
2018-09-07 16:44:50:676 INFO  BrokerService:774 - For help or more information please see: http://activemq.apache.org  
2018-09-07 16:44:50:676 INFO  Main:39 - Success start BrokerService ........  
2018-09-07 16:44:50:754 INFO  Sender:40 - Message has been sent success. Message contains that - 'Message for send'  
2018-09-07 16:44:50:816 INFO  Receiver:38 - Success received message. Message contains that - 'Message for send'  
2018-09-07 16:44:50:832 INFO  BrokerService:836 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57665-1536327890504-0:1) is shutting down  
2018-09-07 16:44:50:832 INFO  TransportConnector:294 - Connector tcp://127.0.0.1:61616 stopped  
2018-09-07 16:44:50:832 INFO  PListStoreImpl:396 - PListStore:[E:\Projects\jmstest-origin\jmstest-master\activemq-data\localhost\tmp_storage] stopped  
2018-09-07 16:44:50:832 INFO  KahaDBStore:264 - Stopping async queue tasks  
2018-09-07 16:44:50:832 INFO  KahaDBStore:278 - Stopping async topic tasks  
2018-09-07 16:44:50:832 INFO  KahaDBStore:306 - Stopped KahaDB  
2018-09-07 16:44:50:957 INFO  BrokerService:896 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57665-1536327890504-0:1) uptime 0.719 seconds  
2018-09-07 16:44:50:957 INFO  BrokerService:898 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57665-1536327890504-0:1) is shutdown  
2018-09-07 16:44:50:957 INFO  Main:43 - Success stop BrokerService ........  
  
	-----------------------------------------------------------  
	
2018-09-07 16:47:12:964 INFO  Main:23 - Start application jmstest ..............  
2018-09-07 16:47:12:979 INFO  Main:24 - MODE = DUPS_OK_ACKNOWLEDGE  
2018-09-07 16:47:12:979 INFO  Main:27 - Success create sender object........  
2018-09-07 16:47:12:979 INFO  Main:30 - Success create receiver object ........  
true  
2018-09-07 16:47:13:057 INFO  BrokerService:683 - Using Persistence Adapter: KahaDBPersistenceAdapter[E:\Projects\jmstest-origin\jmstest-master\activemq-data\localhost\KahaDB]  
2018-09-07 16:47:13:182 INFO  MessageDatabase:191 - KahaDB is version 6  
2018-09-07 16:47:13:260 INFO  PListStoreImpl:371 - PListStore:[E:\Projects\jmstest-origin\jmstest-master\activemq-data\localhost\tmp_storage] started  
2018-09-07 16:47:13:401 INFO  BrokerService:746 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57687-1536328033260-0:1) is starting  
2018-09-07 16:47:13:432 INFO  TransportServerThreadSupport:69 - Listening for connections at: tcp://127.0.0.1:61616  
2018-09-07 16:47:13:432 INFO  TransportConnector:263 - Connector tcp://127.0.0.1:61616 started  
2018-09-07 16:47:13:432 INFO  BrokerService:773 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57687-1536328033260-0:1) started  
2018-09-07 16:47:13:432 INFO  BrokerService:774 - For help or more information please see: http://activemq.apache.org  
2018-09-07 16:47:13:448 INFO  Main:39 - Success start BrokerService ........  
2018-09-07 16:47:13:542 INFO  Sender:40 - Message has been sent success. Message contains that - 'Message for send'  
2018-09-07 16:47:13:589 INFO  Receiver:38 - Success received message. Message contains that - 'Message for send'  
2018-09-07 16:47:13:620 INFO  BrokerService:836 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57687-1536328033260-0:1) is shutting down  
2018-09-07 16:47:13:620 INFO  TransportConnector:294 - Connector tcp://127.0.0.1:61616 stopped  
2018-09-07 16:47:13:620 INFO  PListStoreImpl:396 - PListStore:[E:\Projects\jmstest-origin\jmstest-master\activemq-data\localhost\tmp_storage] stopped  
2018-09-07 16:47:13:620 INFO  KahaDBStore:264 - Stopping async queue tasks  
2018-09-07 16:47:13:620 INFO  KahaDBStore:278 - Stopping async topic tasks  
2018-09-07 16:47:13:620 INFO  KahaDBStore:306 - Stopped KahaDB  
2018-09-07 16:47:13:760 INFO  BrokerService:896 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57687-1536328033260-0:1) uptime 0.750 seconds  
2018-09-07 16:47:13:760 INFO  BrokerService:898 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57687-1536328033260-0:1) is shutdown  
2018-09-07 16:47:13:760 INFO  Main:43 - Success stop BrokerService ........	  
	  
	-----------------------------------------------------------  
	  
2018-09-07 16:49:34:286 INFO  Main:24 - Start application jmstest ..............  
2018-09-07 16:49:34:286 INFO  Main:25 - MODE = CLIENT_ACKNOWLEDGE  
2018-09-07 16:49:34:286 INFO  Main:28 - Success create sender object........  
2018-09-07 16:49:34:286 INFO  Main:31 - Success create receiver object ........  
true  
2018-09-07 16:49:34:380 INFO  BrokerService:683 - Using Persistence Adapter: KahaDBPersistenceAdapter[E:\Projects\jmstest-origin\jmstest-master\activemq-data\localhost\KahaDB]  
2018-09-07 16:49:34:521 INFO  MessageDatabase:191 - KahaDB is version 6  
2018-09-07 16:49:34:583 INFO  PListStoreImpl:371 - PListStore:[E:\Projects\jmstest-origin\jmstest-master\activemq-data\localhost\tmp_storage] started  
2018-09-07 16:49:34:724 INFO  BrokerService:746 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57710-1536328174599-0:1) is starting  
2018-09-07 16:49:34:755 INFO  TransportServerThreadSupport:69 - Listening for connections at: tcp://127.0.0.1:61616  
2018-09-07 16:49:34:755 INFO  TransportConnector:263 - Connector tcp://127.0.0.1:61616 started  
2018-09-07 16:49:34:755 INFO  BrokerService:773 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57710-1536328174599-0:1) started  
2018-09-07 16:49:34:755 INFO  BrokerService:774 - For help or more information please see: http://activemq.apache.org  
2018-09-07 16:49:34:771 INFO  Main:40 - Success start BrokerService ........  
2018-09-07 16:49:34:849 INFO  Sender:40 - Message has been sent success. Message contains that - 'Message for send'  
2018-09-07 16:49:34:943 INFO  Receiver:38 - Success received message. Message contains that - 'Message for send'  
2018-09-07 16:49:34:990 INFO  BrokerService:836 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57710-1536328174599-0:1) is shutting down  
2018-09-07 16:49:34:990 INFO  TransportConnector:294 - Connector tcp://127.0.0.1:61616 stopped  
2018-09-07 16:49:34:990 INFO  PListStoreImpl:396 - PListStore:[E:\Projects\jmstest-origin\jmstest-master\activemq-data\localhost\tmp_storage] stopped  
2018-09-07 16:49:34:990 INFO  KahaDBStore:264 - Stopping async queue tasks  
2018-09-07 16:49:34:990 INFO  KahaDBStore:278 - Stopping async topic tasks  
2018-09-07 16:49:34:990 INFO  KahaDBStore:306 - Stopped KahaDB  
2018-09-07 16:49:35:146 INFO  BrokerService:896 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57710-1536328174599-0:1) uptime 0.828 seconds  
2018-09-07 16:49:35:146 INFO  BrokerService:898 - Apache ActiveMQ 5.15.4 (localhost, ID:OFFICE14915-57710-1536328174599-0:1) is shutdown  
2018-09-07 16:49:35:146 INFO  Main:44 - Success stop BrokerService ........	  
  
	-----------------------------------------------------------  
 
