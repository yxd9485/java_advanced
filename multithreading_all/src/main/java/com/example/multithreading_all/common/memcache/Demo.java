package com.example.multithreading_all.common.memcache;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/8/23 11:07
 */
public class Demo {

    public static void main(String[] args) {
        try {
            // 本地连接 Memcached 服务
            MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
            System.out.println("Connection to server sucessful.");
            String userNameKey = "userName";
            // 1. 添加数据
            Future fo = mcc.set(userNameKey, 900, "admin");
            // 2. 查看存储状态
            System.out.println("set status:" + fo.get());
            // 3.获取值
            System.out.println("userName value in cache - " + mcc.get(userNameKey));
            // 4.替换key的值
            OperationFuture<Boolean> replace = mcc.replace(userNameKey, 1000, "guest");
            // 获取是否更新成功！
            System.out.println("replace status:"+replace.get());
            System.out.println("userName value in cache - " + mcc.get(userNameKey));
            // 6.删除key
            OperationFuture<Boolean> delete = mcc.delete(userNameKey);
            // 获取是否删除成功
            System.out.println("delete status:"+delete.get());
            // 再次判断是否存在
            System.out.println("userName value in cache - " + mcc.get(userNameKey));
            // 关闭连接
            mcc.shutdown();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
