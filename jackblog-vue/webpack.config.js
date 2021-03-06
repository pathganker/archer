const path = require('path')
const webpack = require('webpack')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const ExtractTextPlugin = require('extract-text-webpack-plugin')
const CommonsChunkPlugin = require('webpack/lib/optimize/CommonsChunkPlugin')
const CleanWebpackPlugin = require('clean-webpack-plugin')
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin
const UglifyjsWebpackPlugin = require('uglifyjs-webpack-plugin')
// const CopyWebpackPlugin = require('copy-webpack-plugin')

const env = process.env.NODE_ENV || 'development'
const debug = env !== 'production'

const config = {
  devtool: debug ? 'cheap-module-source-map' : 'hidden-source-map',
  entry: {
    vendor: ['vue','vuex','vue-router'],
    bundle: './src/index'
  },
  output: {
    path: path.join(__dirname, 'dist'),
    filename: debug? '[name].js':'js/[hash:8].[name].js',
    chunkFilename: debug? '[name].js':'js/[name].[chunkhash].js',
    publicPath: '/'
  },
  externals: debug?{}:{
    'vue':'Vue',
    'vue-router':'VueRouter',
    'vuex':'Vuex',
    'vee-validate': 'VeeValidate',
    'iview': 'iView',
  },
  plugins: [
    new webpack.DefinePlugin({
      'process.env':{
        'NODE_ENV': JSON.stringify(env)
      }
    }),    
    new CommonsChunkPlugin({
      name: 'vendor',
      minChunks: Infinity //Infinity
    }),
    new ExtractTextPlugin({ 
      filename: debug?'[name].style.css':'css/[hash:8].style.css', 
      disable: false, allChunks: true 
    }),
    new HtmlWebpackPlugin({
      favicon:path.join(__dirname,'src/favicon.ico'),
      title: '再见老学姐',
      template: path.join(__dirname,'src/index.html'),  //模板文件
      inject:'body',
      hash:false,    //为静态资源生成hash值
      minify:{    //压缩HTML文件
        removeComments:false,    //移除HTML中的注释
        collapseWhitespace:true    //删除空白符与换行符
      }
    }),
    new BundleAnalyzerPlugin(),
    new CleanWebpackPlugin(['dist']),
    // new CopyWebpackPlugin([{
    //   from: 'node_modules/mavon-editor/dist/highlightjs',
    //   to: path.resolve(__dirname, 'dist/highlightjs'), // 插件将会把文件导出于/dist/highlightjs之下
    // }, {
    //     from: 'node_modules/mavon-editor/dist/markdown',
    //     to: path.resolve(__dirname, 'dist/markdown'), // 插件将会把文件导出于/dist/markdown之下
    // }, {
    //     from: 'node_modules/mavon-editor/dist/katex', // 插件将会把文件导出
    //     to: path.resolve(__dirname, 'dist/katex')
    // }]),
  ],
  module: {
    rules: [
      { enforce: 'pre', test: /\.js$/, exclude: /node_modules/, use: ['eslint-loader'] },
      { test: /\.vue$/,
        use: ['vue-loader'], 
        },
      { test: /\.js$/, 
        use: ['babel-loader'],
        exclude: /node_modules|vue\/dist|vue-hot-reload-api|vue-router\/|vue-loader/
      },
      { test: /\.css$/,
        use: ExtractTextPlugin.extract({
          fallback: 'style-loader',
          use: 'css-loader'
          //   loader:'',
          //   options:{
          //     minimize: true,
          //     modules: true,
          //   }
          // }
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
        test: /\.(woff|woff2|ttf|eot|svg|otf)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
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
      components: path.resolve(__dirname, 'src/components'),
      actions: path.resolve(__dirname, 'src/store/actions'),
      getters: path.resolve(__dirname, 'src/store/getters'),
      modules: path.resolve(__dirname, 'src/store/modules'),
      store: path.resolve(__dirname, 'src/store'),
      vue: 'vue/dist/vue.common.js'
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
  }
}

if (debug) {
  config.plugins.push(
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NoEmitOnErrorsPlugin()
  )
  config.devServer = {
    contentBase: path.join(__dirname, 'src'),
    port: 3000,
    host: 'localhost',
    historyApiFallback: true,
    inline: true,
    hot: true,
    watchOptions: {
      aggregateTimeout: 300,
      poll: 1000
    },
  }
} else {
  config.plugins.push(  
    new UglifyjsWebpackPlugin()
  )
}

module.exports = config