package com.yezhihun.demo;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye on 2018/11/13.
 */
public class GameForDateDTO {

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private Data data;
    class Data{
        public Map<String, List<MatchInfo>> getGameMap() {
            return gameMap;
        }

        public void setGameMap(Map<String, List<MatchInfo>> gameMap) {
            this.gameMap = gameMap;
        }

        private Map<String, List<MatchInfo>> gameMap;

        class MatchInfo{
            private String mid;

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }
        }
    }
}
