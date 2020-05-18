const path = require('path');
const webpack = require('webpack');

module.exports = {
	mode: 'development',
	devtool: 'source-map',
	watch: true,
	plugins: [
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery"
        })
    ],
	entry: {
		home: ['./src/index.html', './src/js/map.js', './src/js/form.js', './src/js/index.js'],
	},
	output: {
		filename: 'bundle.js',
		path: path.resolve(__dirname, './public'),
	},
	module: {
		rules: [
			{
				test: /\.css$/i,
				use: ['style-loader', 'css-loader'],
			},
			{
				test: /\.html$/,
				use: [{
					loader: 'file-loader', 
					options: {
						name: '[name].[ext]'
					}
				}]
			},
			{
				test: /\.(png|svg|jpg|gif)$/,
				use: [{
					loader: 'file-loader',
					options: {
						name: 'assets/images/[name].[ext]'
					}
				}]
			},
		],
	},
  resolve: {
    alias: {
      './vue.js': './node_modules/vue/dist/vue.esm.browser.js'
    }
  }
};

