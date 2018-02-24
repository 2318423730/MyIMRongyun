package com.cmx.myimrongyun.util;

import android.content.Context;
import android.util.Log;

import com.cmx.myimrongyun.bean.UnReadMessage;
import com.cmx.myimrongyun.bean.UnReadMessageDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by miliang on 2017/12/19.
 *
 */

public class MyUnReadMessageUtil {
    private static final String TAG = MyUnReadMessageUtil.class.getSimpleName();
    private DBManager mManager;

    public MyUnReadMessageUtil(Context context) {
        mManager = DBManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成unReadMessage记录的插入，如果数据库未创建，先创建watchphone数据库
     *
     * @param unReadMessage
     * @return
     */
    public boolean insertUnreadMessage(UnReadMessage unReadMessage) {
        boolean flag = false;
        flag = mManager.getDaoSession().getUnReadMessageDao().insert(unReadMessage) == -1 ? false : true;
        Log.i(TAG, "insert myMessage :" + flag + "-->" + unReadMessage.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     *
     * @param unReadMessageList
     * @return
     */
    public boolean insertMultUnReadMessage(final List<UnReadMessage> unReadMessageList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (UnReadMessage unReadMessage : unReadMessageList) {
                        mManager.getDaoSession().insertOrReplace(unReadMessage);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     *
     * @param unReadMessage
     * @return
     */
    public boolean updateUnreadMessage(UnReadMessage unReadMessage) {
        boolean flag = false;
        try {
            mManager.getDaoSession().update(unReadMessage);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     *
     * @param unReadMessage
     * @return
     */
    public boolean deleteUnReadMessageCenter(UnReadMessage unReadMessage) {
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(unReadMessage);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * 删除某条记录
     *
     * @return
     */
    public boolean delete(UnReadMessage unReadMessage) {
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(unReadMessage);

            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * 删除所有记录
     *
     * @return
     */
    public boolean deleteAll() {
        boolean flag = false;
        try {

            mManager.getDaoSession().deleteAll(UnReadMessage.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<UnReadMessage> queryAllUnReadMessage() {
        return mManager.getDaoSession().loadAll(UnReadMessage.class);
    }

    /**
     * 根据主键id查询记录
     *
     * @param key
     * @return
     */
    public UnReadMessage queryUnReadMessageById(long key) {
        return mManager.getDaoSession().load(UnReadMessage.class, key);
    }


    /**
     * 使用native sql进行查询操作
     */
    public List<UnReadMessage> queryUnReadMessageByNativeSql(String sql, String[] conditions) {
        return mManager.getDaoSession().queryRaw(UnReadMessage.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     *根据字段名dbId查询记录
     * @return
     */
    public List<UnReadMessage> queryUnReadMessageQueryBuilder(long id) {
        QueryBuilder<UnReadMessage> queryBuilder = mManager.getDaoSession().queryBuilder(UnReadMessage.class);
        return queryBuilder.where(UnReadMessageDao.Properties.DbId.eq(id)).list();
    }

    /**
     * 根据字段名targetid查询记录
     *
     * @param key
     * @return
     */
    public List<UnReadMessage> queryUnReadMessageByTargetId(String key) {
        QueryBuilder<UnReadMessage> queryBuilder = mManager.getDaoSession().queryBuilder(UnReadMessage.class);
        return  queryBuilder.where(UnReadMessageDao.Properties.TargetId.eq(key)).list();
    }
}