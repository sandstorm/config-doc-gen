import * as React from 'react';
import {connect} from 'react-redux';

import {actions, IApplicationState, selectors} from '../../Redux/Store';

import AppSidebarDefaultView from 'src/Components/Organisms/AppSidebarDefaultView';

const mapStateToProps = (state: IApplicationState) => ({
    namespaces: selectors.Ui.ConfigDoc.filteredNamespaces(state),
    properties: selectors.Ui.ConfigDoc.filteredProperties(state),
    selectedItem: selectors.Ui.ConfigDoc.sidebarSelectedItem(state),
});

const mapDispatchToProps = {
    onSelectItem: actions.Ui.ConfigDoc.selectSidebarItem,
}

type SidebarDefaultViewProps = ReturnType<typeof mapStateToProps> & typeof mapDispatchToProps;

class SidebarDefaultView extends React.PureComponent<SidebarDefaultViewProps> {
    public render(): JSX.Element {
        return (<AppSidebarDefaultView {...this.props}/>);
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(SidebarDefaultView);
