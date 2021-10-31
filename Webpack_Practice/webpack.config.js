const { CleanWebpackPlugin } = require('clean-webpack-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const path = require('path')
const { resolve } = require('path')
const UglifyJsPlugin = require('uglifyjs-webpack-plugin')

module.exports = {
  entry: './src/index.js',
  output: {
    filename: 'main.js',
    path: resolve(__dirname, 'dist')
  },
  module: {
    rules: [
      {
        test: /\.css$/,
        use: [
          'style-loader',
          'css-loader'
        ]
      },
      // file-loader
      {
        test: /\.(png|svg|jpe?g|gif)$/,
        loader: 'file-loader',
        options: {
          name: '[hash].[ext]'
        }
      },
      // url-loader
      // {
      //   test: /\.(png|svg|jpe?g|gif)$/,
      //   loader: 'url-loader',
      //   options: {
      //     limit: 100000
      //   }
      // },
    ]
  },
  plugins: [
    new CleanWebpackPlugin(),
    new HtmlWebpackPlugin(),
    new UglifyJsPlugin(),
  ],
  devServer: {
    host: '127.0.0.1',
    compress: true,
    hot: true,
    port: 9000,
    open: true
  }
}