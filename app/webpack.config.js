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
		home: ['./public/src/js/map.js','./public/src/js/form.js'],
	},
	output: {
		filename: '[name].bundle.js',
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

