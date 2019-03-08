package com.ssm.demo.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * @Author hsir
 * @Date 13:22 2019/2/22/022
 * @Param 
 * @return 
 **/
public class PageResult implements Serializable {
        //总记录数
        private int totalCount;
        //当前页数
        private int currPage;
        //数据列表
        private List<?> list;
        //每页记录数
        private int pageSize;
        //总页数
        private int totalPage;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public List<?> getList() {
            return list;
        }

        public void setList(List<?> list) {
            this.list = list;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public PageResult(int totalCount, int currPage, List<?> list, int pageSize) {
            this.totalCount = totalCount;
            this.currPage = currPage;
            this.list = list;
            this.pageSize = pageSize;
            this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
        }
}
