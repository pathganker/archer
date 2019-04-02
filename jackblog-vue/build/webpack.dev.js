const path = require('path')
const webpack = require('webpack')
const merge = require('webpack-merge')
const common = require('./webpack.common.js')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const CopyWebpackPlugin=require('copy-webpack-plugin')
module.exports = merge(common, {
  entry: {
    vendor: ['vue','vuex','vue-router'],
    bundle: './src/index_dev'
  },
  devtool: 'inline-source-map',
  plugins:[
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NoEmitOnErrorsPlugin(),
    new HtmlWebpackPlugin({
      favicon:path.join(__dirname,'../src/favicon.ico'),
      title: '再见老学姐',
      template: path.join(__dirname,'../src/index_dev.html'),  //模板文件
      inject:'body',
      hash: true,    //为静态资源生成hash值
      minify:{    //压缩HTML文件
        removeComments:false,    //移除HTML中的注释
        collapseWhitespace:true    //删除空白符与换行符
      }
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
    {
      from: path.resolve(__dirname, '../../../../../ROOT/upload'),
      to: 'upload'
    },{
      from: path.resolve(__dirname, '../static/js'),
      to: 'static/js'
    }
    ])
  ],
  devServer: {
    contentBase: path.join(__dirname, 'src'),
    port: 3000,
    host: 'localhost',
    historyApiFallback: true,
    inline: true,
    hot: true,
    watchOptions: {
      aggregateTimeout: 300,
      poll: 1000
    }
  },

});