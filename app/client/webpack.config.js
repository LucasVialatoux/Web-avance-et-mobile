const path = require('path');
const webpack = require('webpack');

module.exports = {
	plugins: [
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery"
        })
    ],
	entry: {
		home: ['./src/index.html', './src/js/map.js', './src/js/form.js'],
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
	}
};

