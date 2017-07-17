package com.lxp.websocket.po.result;


import java.util.List;

import com.lxp.websocket.po.BigGroup;
import com.lxp.websocket.po.FriendGroup;
import com.lxp.websocket.po.StatusUser;

/**
 * Created by pz on 16/11/23.
 * 这个类主要是对应于layim初始化的数据接口，里面包含mine，friend，group数据
 */
public class BaseDataResult {
    public StatusUser getMine() {
        return mine;
    }

    public void setMine(StatusUser mine) {
        this.mine = mine;
    }

    public List<FriendGroup> getFriend() {
        return friend;
    }

    public void setFriend(List<FriendGroup> friend) {
        this.friend = friend;
    }

    public List<BigGroup> getGroup() {
        return group;
    }

    public void setGroup(List<BigGroup> group) {
        this.group = group;
    }

    private StatusUser mine;
    private List<FriendGroup> friend;
    private List<BigGroup> group;
}
