"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var React = require("react");
var ReactDOM = require("react-dom");
var react_redux_1 = require("react-redux");
var App_1 = require("./Components/Organisms/App");
require("./index.css");
var configureStore_1 = require("./Redux/configureStore");
var store = configureStore_1.default();
ReactDOM.render(<react_redux_1.Provider store={store}>
    <App_1.default />
  </react_redux_1.Provider>, document.getElementById('root'));
