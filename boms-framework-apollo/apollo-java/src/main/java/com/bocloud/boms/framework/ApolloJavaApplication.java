package com.bocloud.boms.framework;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;

/**
 *
 * @author guo shuang
 * Apollo整合java的实例
 */
public class ApolloJavaApplication {

    public static void main(String[] args){

        //设置环境（开发演示用）
        System.setProperty("env","DEV");

        Config config = ConfigService.getAppConfig();

//        getValue(config);

//        addChangeListner(config);

        getRequestTimeOut(config);

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 配置修改监听回调
     * API提供的监听机制
     * 当我们在Portal中进行修改配置时，就触发监听事件
     * @param config
     */
    public static void addChangeListner(Config config){

        config.addChangeListener(new ConfigChangeListener() {

            @Override
            public void onChange(ConfigChangeEvent configChangeEvent) {

                System.out.println("发生修改数据的命名空间是:" + configChangeEvent.getNamespace());

                for(String key : configChangeEvent.changedKeys()){

                    ConfigChange configChange = configChangeEvent.getChange(key);

                    System.out.println(
                            String.format("发现修改-配置key:%s,原来的值:%s,修改后的值:%s,操作类型:%s",
                            configChange.getPropertyName(),
                            configChange.getOldValue(),
                            configChange.getNewValue(),
                            configChange.getChangeType()));

                }
            }
        });

    }

    /**
     * 通过ConfigService得到Config对象,config.getProperty()方法可以传入想获取的配置key
     * defaultValue是当配置中心找不到配置的时候返回默认值，避免空指针异常
     * @param config
     */
    public static void getValue(Config config){

        String key = "guo";

        String defaultValue = "guo-test";

        String username = config.getProperty(key,defaultValue);

        System.out.println("username:" + username);

    }

    public  static  void  getRequestTimeOut(Config config){

        Integer defaultRequestTimeout = 200;
        Integer requestTimeout = config.getIntProperty("requestTimeout", defaultRequestTimeout);
        System.out.println(requestTimeout);
    }


}
