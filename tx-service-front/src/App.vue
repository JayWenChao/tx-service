<template>
  <div class="CopClass">
    <el-row>
      <el-col :span="5">
        <el-menu :default-active="menuActive" @select='handleSelect'>
          <el-submenu v-for="(item, index) in menus" :key="index" :index="`${item.id}`">
            <template slot="title">
              <span>{{ item.name }}</span>
            </template>
            <template v-if='item.children && item.children.length'>
              <el-menu-item v-for='(_item, _index) in item.children' :key="_index" :index="item.id +'-'+_item.id">
                {{_item.name }}
              </el-menu-item>
            </template>
          </el-submenu>
        </el-menu>
      </el-col>
      <el-col :span="19" style="text-align: right;">
        <el-radio v-model="priceTop" label="0" @change="changePriceTop">小额课</el-radio>
        <el-radio v-model="priceTop" label="1" @change="changePriceTop">大额课</el-radio>
      </el-col>
      <el-col :span="19">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column v-for='(item, index) in columnMap' :key="index" :prop="item.value" :label="item.name">
        </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <a :href="scope.row.link" target="_blank">
                <i class="el-icon-view"></i>
              </a>
            </template>
          </el-table-column>
        </el-table>


        <el-pagination style="text-align: right;" v-if="pager.total > pager.size" layout="prev, pager, next" :total="pager.total" @current-change="handlePager">
        </el-pagination>
      </el-col>
    </el-row>

  </div>
</template>
<script>
  import axios from 'axios';

  import qs from 'qs'

  export default {
    name: 'CopName',
    data() {
      return {
        menuActive:'',
        priceTop: '0',
        columnMap: [
          {name: '价格', value: 'price',},
          {name: '人数', value: 'users',},
          {name: '标题', value: 'title',},
          {name: '链接', value: 'link',},
          {name: '课程所属机构', value: 'agency',},
        ],
        tableData: [],
        pager: {
          current: 1,
          size: 10,
          total: 0,
        },
        menus: [
        ],
        queryFirstId: 0,
        seedId: 0,
      }
    },
    methods: {
      changePriceTop(){
        this.handleQuery()
      },
      handleQuery: function () {


        axios.post("/api/query", qs.stringify({
          numPage :this.pager.current,
          seedId : this.seedId,
          priceTop : this.priceTop
        })).then((reponse) =>{
          let res=reponse.data
          if(res.code===200){
            let data=res.data
            this.pager.total=data.count
            this.tableData=data.result
          }
        })
      },
      handleMenu: function () {
        axios.post("/api/menu").then((reponse) =>{
          let res=reponse.data
          if(res.code===200){
            this.menus=res.data
            //初始化選中第一個
            this.seedId=this.menus[0].children[0].id
            this.menuActive=`${this.menus[0].id}-${this.menus[0].children[0].id}`
            this.handleQuery()
          }
        })
      },
      handleSelect(index) {
        this.queryFirstId = index.split('-')[0];
        this.seedId = index.split('-')[1];
        this.pager.current = 1; // 筛选默认返回第一页

        this.handleQuery();
      },
      handlePager(p) {
        this.pager.current = p;
        this.handleQuery();
      },
    },
    mounted() {
      this.handleMenu()
    }
  }
</script>
