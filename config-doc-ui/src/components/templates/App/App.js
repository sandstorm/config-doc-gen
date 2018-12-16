import React, {PureComponent} from 'react';
import './App.scss';
import '../../molecules/SidebarNamespaceList/SidebarNamespaceList';
import SidebarNamespaceList from "../../molecules/SidebarNamespaceList/SidebarNamespaceList";
import PropTypes from 'prop-types';

class App extends PureComponent {
    static propTypes = {
        sidebar: PropTypes.shape({
            namespaceNames: PropTypes.arrayOf(PropTypes.string).isRequired,
            onSelectNamespace: PropTypes.func.isRequired,
        }),
    };

    render() {
        const {sidebar} = this.props;
        return (
            <div className="app">
                <header className="app-header">
                    Header
                </header>
                <div className="sidebar-container">
                    <SidebarNamespaceList namespaces={sidebar.namespaceNames} onClickItem={sidebar.onSelectNamespace}/>
                </div>
            </div>
        );
    }
}

export default App;
