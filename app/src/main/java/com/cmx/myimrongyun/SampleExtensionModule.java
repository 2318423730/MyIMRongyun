package com.cmx.myimrongyun;

import java.util.ArrayList;
import java.util.List;


import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.emoticon.IEmoticonTab;
import io.rong.imkit.plugin.CombineLocationPlugin;
import io.rong.imkit.plugin.DefaultLocationPlugin;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.plugin.ImagePlugin;

import io.rong.imlib.model.Conversation;

public class SampleExtensionModule extends DefaultExtensionModule {
    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
//        super.getPluginModules(conversationType);  如果需要对红包进行排序可从父类中的 getPluginModules 集合中过滤取出 JrmfExtensionModule
        List<IPluginModule> pluginModuleList = new ArrayList<>();
        pluginModuleList.add(new SamplePlugin());
        pluginModuleList.add(new CombineLocationPlugin());
        pluginModuleList.add(new DefaultLocationPlugin());
        pluginModuleList.add(new ImagePlugin());
        /*pluginModuleList.add(new AudioPlugin());
        pluginModuleList.add(new VideoPlugin());*/

        // 此时扩展区域的展示顺序应该为 : 示例、位置、图片、红包
        return pluginModuleList;
    }

    @Override
    public List<IEmoticonTab> getEmoticonTabs() {
        return super.getEmoticonTabs();
    }
}