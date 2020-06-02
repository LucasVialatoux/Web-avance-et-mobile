const path = require('path');
const webpack = require('webpack');
const VuetifyLoaderPlugin = require('vuetify-loader/lib/plugin')
const VueLoaderPlugin = require('vue-loader/lib/plugin')

module.exports = {
	mode: 'development',
	devtool: 'source-map',
	watch: true,
	entry: {
		home: ['./src/index.html', './src/js/index.js', './src/js/map.js'],
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
			{
				test: /\.(woff|woff2|eot|ttf|otf)$/,
				use: [
					'file-loader',
			    ],
			},
			{
				test: /\.vue$/,
				loader: 'vue-loader'
			},
			{
				test: /\.s(c|a)ss$/,
				use: [
					'vue-style-loader',
					'css-loader',
					{
						loader: 'sass-loader',
						// Requires sass-loader@^7.0.0
						options: {
							implementation: require('sass'),
							fiber: require('fibers'),
							indentedSyntax: true // optional
						},
						// Requires sass-loader@^8.0.0
						options: {
							implementation: require('sass'),
							sassOptions: {
								fiber: require('fibers'),
								indentedSyntax: true // optional
							},
						},
					},
				],
			}
		],
	},
	plugins: [
		// make sure to include the plugin!
		new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery"
        }),
		new VuetifyLoaderPlugin(),
		new VueLoaderPlugin(),
	]
};

