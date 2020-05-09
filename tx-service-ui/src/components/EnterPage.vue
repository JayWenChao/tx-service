<template>
  <div class="CopClass">
    <el-row>
      <el-col :span="5">
        <el-menu
          default-active="2001"
          @select='handleSelect'
        >
          <el-submenu
            v-for="(item, index) in menus"
            :key="index"
            :index="item.id"
          >
            <template slot="title">
              <span>{{ item.name }}</span>
            </template>
            <template v-if='item.children && item.children.length'>
              <el-menu-item
                v-for='(_item, _index) in item.children'
                :key="_index"
                :index="item.id +'-'+_item.id"
              >{{ _item.name }}
              </el-menu-item>
            </template>
          </el-submenu>
        </el-menu>
      </el-col>
      <el-col :span="19">
        <!-- <el-button type="primary" @click="query">xxx</el-button> -->
        <el-table
          :data="tableData"
          style="width: 100%">
          <el-table-column
            v-for='(item, index) in columnMap'
            :key="index"
            :prop="item.value"
            :label="item.name"
          >
            <template slot-scope="scope">
              {{ scope.row[item.value] }}
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
          >
            <template slot-scope="scope">
              <a :href="scope.row.link" target="_blank">
                <i class="el-icon-view"></i>
              </a>
            </template>
          </el-table-column>
        </el-table>

        <el-pagination
          v-if="pager.total > pager.size"
          layout="prev, pager, next"
          :total="pager.total"
          @change="handlePager"
        >
        </el-pagination>
      </el-col>
    </el-row>
  </div>
</template>
<script>
    import axios from 'axios';
    import MokcData from './test.json';

    export default {
        name: 'CopName',
        mounted() {
            // console.log(11, MokcData)
        },
        data() {
            return {
                isMock: true,
                columnMap: [
                    {name: '课程名', value: 'users',},
                    {name: '价格', value: 'price',},
                    {name: '报名人数', value: 'users',},
                    {name: '最近学习人数', value: 'users',},
                    {name: '购买人数', value: 'users',},
                    {name: '课程所属机构', value: 'agency',},
                ],
                tableData: [],
                pager: {
                    current: 1,
                    size: 10,
                    total: 0,
                },
                menus: [
                    {
                        name: 'IT . 互联网', id: '1001',
                        children: [
                            {name: '互联网产品', id: '2001',},
                            {name: '编程语言', id: '3001',},
                            {name: '前端', id: '4001',},
                        ],
                    },
                    {
                        name: '设计 . 创建', id: '1002',
                        children: [
                            {name: '平面设计', id: '2011',},
                        ],
                    },
                ],
                queryFirstId: 0,
                querySecondtId: 0,
            }
        },
        methods: {
            handleQuery: function () {
                if (this.isMock) {
                    this.tableData = MokcData.data.records || [];
                }

                console.log(11, this.pager.current);
                this.pager.total = 30; // 此处返回总条数
                // axios.post("/api/query", {
                //   id1: this.queryFirstId, //此处传二级id
                //   id2: this.querySecondtId, //此处传二级id
                // }).then((reponse) =>{
                //     console.log("response: "+reponse.data)
                // })
            },
            handleSelect(index) {
                this.queryFirstId = index.split('-')[0];
                this.querySecondtId = index.split('-')[1];
                this.pager.current = 1; // 筛选默认返回第一页

                this.handleQuery();
            },
            handlePager(p) {
                this.pager.current = p;
                this.handleQuery();
            },
        }
    }
</script>
