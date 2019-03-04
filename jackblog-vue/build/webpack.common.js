const path = require('path')
const webpack = require('webpack')
const CleanWebpackPlugin = require('clean-webpack-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const ExtractTextPlugin = require('extract-text-webpack-plugin')

module.exports ={
  entry: {
    vendor: ['vue','vuex','vue-router'],
    bundle: './src/index'
  },
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'js/[hash:8].[name].js',
    chunkFilename: 'js/[name].[chunkhash].js',
    publicPath: '/'
  },
  plugins: [
    new CleanWebpackPlugin(['dist']),
    new webpack.HashedModuleIdsPlugin(),
    new webpack.optimize.CommonsChunkPlugin({
      name: 'vendor',
      minChunks: Infinity //Infinity
    }),
    new webpack.DefinePlugin({
      'process.env.NODE_ENV': JSON.stringify('production')
    }),
    new HtmlWebpackPlugin({
      favicon:path.join(__dirname,'..y/src/favicon.ico'),
      title: '学姐不玩了',
      template: path.join(__dirname,'../src/index.html'),  //模板文件
      inject:'body',
      hash:false,    //为静态资源生成hash值
      minify:{    //压缩HTML文件
        removeComments:false,    //移除HTML中的注释
        collapseWhitespace:true    //删除空白符与换行符
      }
    }),
    new ExtractTextPlugin({ 
      filename: 'css/[hash:8].style.css', 
      disable: false, 
      allChunks: true 
    }),
  ],
  module: {
    rules: [
      { enforce: 'pre', test: /\.js$/, exclude: /node_modules/, use: ['eslint-loader'] },
      { test: /\.vue$/,
        use: ['vue-loader'], 
        include: path.join(__dirname,'src')}, 
      { test: /\.js$/, 
        use: ['babel-loader'],
        exclude: /node_modules|vue\/dist|vue-hot-reload-api|vue-router\/|vue-loader/
      },
      { test: /\.css$/,
        use: ExtractTextPlugin.extract({
          fallback: 'style-loader',
          use: 'css-loader'
        })
      },
      {
        test: /\.(jpe?g|png|gif)$/i,
        use: [
          {
            loader: 'url-loader',
            options: {
              limit: 10000,
              name: 'images/[hash:8].[name].[ext]'
            }
          },
          {
            loader: 'image-webpack-loader',
            options: {
              mozjpeg: {
                quality: 65
              },
              pngquant:{
                quality: '65-90',
                speed: 4
              },
              svgo:{
                plugins: [
                  {
                    removeViewBox: false
                  },
                  {
                    removeEmptyAttrs: false
                  }
                ]
              },
              gifsicle: {
                optimizationLevel: 7,
                interlaced: false
              },
              optipng: {
                optimizationLevel: 7,
                interlaced: false
              }
            }
          }
        ]
      },
      {
        test: /\.(woff|woff2|ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
        use: [{
          loader: 'url-loader',
          options: {
            limit: 10000,
            name: 'fonts/[hash:8].[name].[ext]'
          }
        }]
      }
    ]
  },
  resolve: {
    extensions: ['.js','.vue','.scss','.css'],
    alias: {
      components: path.resolve(__dirname, '../src/components'),
      actions: path.resolve(__dirname, '../src/store/actions'),
      getters: path.resolve(__dirname, '../src/store/getters'),
      modules: path.resolve(__dirname, '../src/store/modules'),
      store: path.resolve(__dirname, '../src/store'),
      vue: 'vue/dist/vue.js'
    }
  },
  node: {
    global: true,
    crypto: 'empty',
    process: true,
    console: false,
    __filename: 'mock',
    __dirname: 'mock',
    Buffer: true,
    setImmediate: true
  },

}