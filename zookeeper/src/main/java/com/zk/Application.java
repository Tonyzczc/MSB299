package com.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;


/**
 * @Author:Controller
 * @date:2020-06-28 10:20 下午
 * ZK Helloword
 **/
public class Application {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello word");
        final ZooKeeper zk = new ZooKeeper("192.168.44.160:2181,192.168.44.161:2181,192.168.44.162:2181", 3000, new Watcher() {
            //Watch 回调方法
            public void process(WatchedEvent event) {
                Event.KeeperState state = event.getState();
                Event.EventType type = event.getType();
                String path = event.getPath();

                switch (state) {
                    case Unknown:
                        break;
                    case Disconnected:
                        break;
                    case NoSyncConnected:
                        break;
                    case SyncConnected:
                        System.out.println("connect"+path);
                        break;
                    case AuthFailed:
                        break;
                    case ConnectedReadOnly:
                        break;
                    case SaslAuthenticated:
                        break;
                    case Expired:
                        break;
                    case Closed:
                        break;
                }
            }
        });
        ZooKeeper.States state = zk.getState();
        switch (state) {
            case CONNECTING:
                System.out.println("int .....");
                break;
            case ASSOCIATING:
                break;
            case CONNECTED:
                System.out.println("ed .........");
                break;
            case CONNECTEDREADONLY:
                break;
            case CLOSED:
                break;
            case AUTH_FAILED:
                break;
            case NOT_CONNECTED:
                break;
        }
        final Stat stat = new Stat();
//        zk.delete("/ooxx",stat.getVersion());
        String pathName = zk.create("/ooxx","text".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE , CreateMode.EPHEMERAL);

        byte[] data = zk.getData("/ooxx", new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println("getData watch: " + event.toString());
                try {
                    zk.getData("/ooxx", this, stat);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, stat);

        System.out.println(new String(data));

        Stat stat1 = zk.setData("/ooxx", "newdata".getBytes(), 0);


        Stat stat2 = zk.setData("/ooxx", "newdata01".getBytes(), stat1.getVersion());
        System.out.println("-------async start----------");
        zk.getData("/ooxx",false,new AsyncCallback.DataCallback(){
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("-------async call back----------");
                System.out.println(ctx.toString());
                System.out.println(new String(data));
            }
        },"abc");
        System.out.println("-------async over----------");


        Thread.sleep(2222222);

    }



}
