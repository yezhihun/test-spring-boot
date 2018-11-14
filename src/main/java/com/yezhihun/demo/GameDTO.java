package com.yezhihun.demo;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by tianye on 2018/11/13.
 */
public class GameDTO {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        public MatchInfo getMatchInfo() {
            return matchInfo;
        }

        public void setMatchInfo(MatchInfo matchInfo) {
            this.matchInfo = matchInfo;
        }

        private MatchInfo matchInfo;


        public class MatchInfo {
            public JSONObject getGoals() {
                return goals;
            }

            public void setGoals(JSONObject goals) {
                this.goals = goals;
            }

            private JSONObject goals;
            private String startTime;
            private String leftTeamId;
            private String leftName;
            private String rightTeamId;
            private String rightName;

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getLeftTeamId() {
                return leftTeamId;
            }

            public void setLeftTeamId(String leftTeamId) {
                this.leftTeamId = leftTeamId;
            }

            public String getLeftName() {
                return leftName;
            }

            public void setLeftName(String leftName) {
                this.leftName = leftName;
            }

            public String getRightTeamId() {
                return rightTeamId;
            }

            public void setRightTeamId(String rightTeamId) {
                this.rightTeamId = rightTeamId;
            }

            public String getRightName() {
                return rightName;
            }

            public void setRightName(String rightName) {
                this.rightName = rightName;
            }

        }
    }
}
