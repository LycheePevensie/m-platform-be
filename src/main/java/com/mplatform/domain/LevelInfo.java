package com.mplatform.domain;

public class LevelInfo {
	private Integer levelId;
	private String levelName;
	private String levelNum;

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getLevelNum() {
		return levelNum;
	}

	public void setLevelNum(String levelNum) {
		this.levelNum = levelNum;
	}

	@Override
	public String toString() {
		return "LevelInfo [levelId=" + levelId + ", levelName=" + levelName + ", levelNum=" + levelNum + "]";
	}

}
