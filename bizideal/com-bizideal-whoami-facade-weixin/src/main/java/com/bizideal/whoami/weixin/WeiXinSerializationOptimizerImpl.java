/**
 * Copyright 1999-2014 dangdang.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bizideal.whoami.weixin;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;
import com.bizideal.whoami.weixin.dto.WechatAccessToken;
import com.bizideal.whoami.weixin.entity.WechatConfig;
import com.bizideal.whoami.weixin.entity.WechatRequestMessageText;
import com.bizideal.whoami.weixin.entity.WechatResponseMessageText;
import com.bizideal.whoami.weixin.entity.WechatTotalUser;
import com.bizideal.whoami.weixin.entity.WechatUserInfo;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * This class must be accessible from both the provider and consumer
 *
 * @author lishen
 */
public class WeiXinSerializationOptimizerImpl implements SerializationOptimizer {

    public Collection<Class> getSerializableClasses() {
    	
        List<Class> classes = new LinkedList<Class>();
        classes.add(WechatConfig.class);
        classes.add(WechatUserInfo.class);
        classes.add(WechatTotalUser.class);
        classes.add(WechatAccessToken.class);
        classes.add(WechatRequestMessageText.class);
        classes.add(WechatResponseMessageText.class);
        return classes;
    }
}
