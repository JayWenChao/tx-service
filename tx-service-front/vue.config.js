const path = require("path");

module.exports = {
    // webpack配置 - 简单配置方式
    configureWebpack: {
        resolve: {
            alias: {
                // 别名
                vue$: "vue/dist/vue.esm.js", //加上这一句
            }
        }
    },
    devServer: {
        port: 8091,
        hotOnly: true,
        proxy: {
            '/api': {
                ws: false,
                target: 'http://localhost:8088',
                changeOrigin: true

            },
        }
    },
}