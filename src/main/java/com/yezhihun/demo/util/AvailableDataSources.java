package com.yezhihun.demo.util;

public enum AvailableDataSources {
    READ() {
        @Override
        public String toString() {
            return "READ";
        }
    },


    WRITE() {
        @Override
        public String toString() {
            return "WRITE";
        }
    },

    CMS() {
        @Override
        public String toString() {
            return "CMS";
        }
    },

    MGMT() {
        @Override
        public String toString() {
            return "MGMT";
        }
    }

}
