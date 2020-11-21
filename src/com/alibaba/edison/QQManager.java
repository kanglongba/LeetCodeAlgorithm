package com.alibaba.edison;

import java.util.*;

/**
 * Created by qhl on 2020/11/16.
 * Q3 如果你是手机QQ的研发人员，请设计一个好友管理系统的架构，写出关键考虑点，包括不限于接口，数据结构，算法和性能优化方案。
 */
public class QQManager {


    //群->在线/不在线/->等级->QQ号
    //构建关系链，关系链间的数据同步（状态、删除、新增）
    //图的结构

    interface Manager {

        HashMap<Long, QQModel> getFriendsByQQNo(long qqNumber);

        QQModel queryQQ(long qqNumber);

        boolean updateStatus(long qqNumber, int newStatus);

        boolean addFriend(long qqNumber);

        boolean removeFriend(long qqNumber);

        boolean modifyFriendGroup(long qqNumber, long newGroupId);

    }


    public class QQAccount {
        private long qqNumber;

        private int status;

        private int level;

        private TreeMap<Group,List<QQModel>> qqFriends;

        private HashMap<Long, QQDetailModel> qqFriendModels;

    }

    public class QQModel implements Comparator<QQModel> {

        private long qqNumber;

        private long groupId;

        private int status;

        private int level;


        @Override
        public int compare(QQModel o1, QQModel o2) {
            //在线/不在线/->等级->QQ号
            return 0;
        }

    }

    public class QQDetailModel {
        //红钻、绿钻
        private long qqNumber;
    }

    public class Group implements Comparator<Group> {
        private long grroupId;
        private int range;

        @Override
        public int compare(Group o1, Group o2) {
            return 0;
        }
    }

    interface OnFriendsChanges {
        void onChange(long qqNumber, HashMap<Long, QQModel> map);
    }
}
