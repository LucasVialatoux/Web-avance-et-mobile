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
		home: ['./src/js/map.js','./src/js/form.js'],
	},
	output: {
		filename: '[name].bundle.js',
		path: path.resolve(__dirname, './src'),
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

