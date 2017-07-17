package com.lxp.websocket.po.message;

/**
 *   username: "范月盘"
 ,avatar: ""
 ,id: 4803920
 ,type:"friend"
 ,content: "我在测试发消息"
 ,timestamp: new Date().getTime()
 * Created by pz on 16/11/23.
 */
public class ToClientTextMessage {
    

    private String username;
    private int id;
    private int cid;
    private int fromid;
    private boolean mine;
    private String type;
    private String content;
    private long timestamp;
    private String avatar;
    
    
    
    public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public boolean isMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public int getFromid() {
		return fromid;
	}

	public void setFromid(int fromid) {
		this.fromid = fromid;
	}

	public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

	@Override
	public String toString() {
		return "ToClientTextMessage [username=" + username + ", id=" + id
				+ ", fromid=" + fromid + ", type=" + type + ", content="
				+ content + ", timestamp=" + timestamp + ", avatar=" + avatar
				+ "]";
	}

    
    
}
