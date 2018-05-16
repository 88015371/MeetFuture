## greenDAO 使用小结

之前项目中开始使用GreenDAO， 一直都是只是会用，没有对其进行归纳总结。这次接着升级GreenDAO 3.0的机会，将之前自己对其使用的一些看法小结一下。
 
       
使用到的几个核心类有：DaoMaster, OpenHelper, DaoSession, XXXDao等。 

        
DaoMaster: 正如其名，这个类是数据库的统领，也是整个操作的入口。其管理着数据库， 数据库版本号和一些数据库相关的信息。DaoMaster可以用来生成下面将要说到DaoSession。在构造函数中需要对所有的Dao通过registerDaoClass()进行注册，以便DaoMaster对其进行管理。
 
       
 
OpenHelper: 根据官方文档，GreenDAO中总共有四种OpenHelper，分别是 OpenHelper, DevOpenHelper，EncryptedOpenHelper 和EncryptedDevOpenHelper；前两个是用来操作未加密数据库，后两个是用来操作加密数据库；DevOpenHelper 和 EncryptedDevOpenHelper 会在升级的时候删除所有表并重建，所以只建议在开发时使用。 

       
OpenHelper的用来对数据库进行管理，如创建表，删除表，获取可读写数据库等。另外，OpenHelper还有对数据库创建，数据库升级 和 数据库开启等事件的监听，分别在onCreate(), onUpgrade(), onOpen()。 

       
DaoSession: 管理所有的XXXDao, DaoSession中也会有增删改查的方法， 其可以直接通过要插入实体的类型找到对应的XXXDao后再进行操作。当没有找到实体对应的Dao时，会抛出 org.greenrobot.greendao.DaoException: No DAO registered for class XXX 的错误。 

       
XXXDao： 对实体进行操作，有比DaoSession更丰富的操作，如loadAll, insertInTx. 
       
其实，之前还真不知道DaoSession中也一些基本的持久化方法，在写这篇博客的过程中看了下源码和官方文档才发现这个。

>GreenDAO 用法：

		helper = new DaoMaster.DevOpenHelper( this, "notes-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        noteDao = daoSession.getNoteDao();


