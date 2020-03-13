const path = require('path');
const webpack = require('webpack');

module.exports = {
	entry: './public/src/js/map.js',
	output: {
		filename: 'bundle.js',
		path: path.resolve(__dirname, './public/dist'),
	},
	module: {
		rules: [
			{
		     	test: /\.css$/,
		     	use: [
		       		'style-loader',
		       		'css-loader',
		     	],
		   	},
		],
	}
};

