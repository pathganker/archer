const path = require('path')
const webpack = require('webpack')
const CleanWebpackPlugin = require('clean-webpack-plugin')
const HtmlWebpalckPlugin = require('html-webpack-plugin')

module.exports ={
  entry: {
    vendor: ['vue','vuex','vue-router'],
    bundle: '../src/index'
  },
  plugins: [
    new CleanWebpackPlugin(['dist']),
    new HtmlWebpalckPlugin({
      title: 'Production'
    }),
    new webpack.HashedModuleIdsPlugin(),
    new webpack.optimize.CommonsChunkPlugin({
      name: 'vendor',
      minChunks: Infinity //Infinity
    }),
    
  ],
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'js/[hash:8].[name].js',
    chunkFilename: 'js/[name].[chunkhash].js',
    publicPath: '/'
  }
}