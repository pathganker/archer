const path = require('path')
const merge = require('webpack-merge')
const UglifyJSPlugin = require('uglifyjs-webpack-plugin')
const common = require('./webpack.common.js')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin
const CopyWebpackPlugin=require('copy-webpack-plugin')
module.exports = merge(common, {
  entry: {
    vendor: ['vue','vuex','vue-router'],
    bundle: './src/index'
  },
  devtool: 'source-map',
  plugins: [
    new HtmlWebpackPlugin({
      favicon: path.join(__dirname,'../src/favicon.ico'),
      title: '再见老学姐',
      template: path.join(__dirname,'../src/index.html'),  //模板文件
      inject:'body',
      hash: true,    //为静态资源生成hash值
      minify:{    //压缩HTML文件
        removeComments: true,    //移除HTML中的注释
        collapseWhitespace: true    //删除空白符与换行符
      }
    }),
    new BundleAnalyzerPlugin(),
    new UglifyJSPlugin({
      sourceMap: true
    }),
    new CopyWebpackPlugin([{
      from: path.resolve(__dirname, '../static/WEB-INF'),
      to: 'WEB-INF'
    },
    {
      from: path.resolve(__dirname, '../static/css'),
      to: 'static/css'
    },
    {
      from: path.resolve(__dirname, '../static/fonts'),
      to: 'static/fonts'
    },
    ])
  ],
  externals: {
    'vue':'Vue',
    'vue-router':'VueRouter',
    'vuex':'Vuex',
    'vee-validate': 'VeeValidate',
    'iview': 'iView',
  },
});