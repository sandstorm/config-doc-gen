"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
Object.defineProperty(exports, "__esModule", { value: true });
var react_1 = require("react");
require("./App.scss");
require("../../molecules/SidebarNamespaceList/SidebarNamespaceList");
var SidebarNamespaceList_1 = require("../../molecules/SidebarNamespaceList/SidebarNamespaceList");
var prop_types_1 = require("prop-types");
var App = /** @class */ (function (_super) {
    __extends(App, _super);
    function App() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    App.prototype.render = function () {
        var sidebar = this.props.sidebar;
        return (<div className="app">
                <header className="app-header">
                    Header
                </header>
                <div className="sidebar-container">
                    <SidebarNamespaceList_1.default namespaces={sidebar.namespaceNames} onClickItem={sidebar.onSelectNamespace}/>
                </div>
            </div>);
    };
    App.propTypes = {
        sidebar: prop_types_1.default.shape({
            namespaceNames: prop_types_1.default.arrayOf(prop_types_1.default.string).isRequired,
            onSelectNamespace: prop_types_1.default.func.isRequired,
        }),
    };
    return App;
}(react_1.PureComponent));
exports.default = App;
