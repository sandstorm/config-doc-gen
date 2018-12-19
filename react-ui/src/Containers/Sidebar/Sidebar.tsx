import * as React from 'react';
import {connect} from 'react-redux';

import {actions, IApplicationState, selectors} from '../../Redux/Store';

import AppSidebar from "../../Components/Organisms/AppSidebar/AppSidebar";
import SidebarDefaultView from '../SidebarDefaultView';

const mapStateToProps = (state: IApplicationState) => ({
    selectedItem: selectors.Ui.ConfigDoc.sidebarSelectedItem(state),
    viewMode: selectors.Ui.ConfigDoc.sidebarViewMode(state),
});

const mapDispatchToProps = {
    onViewModeSelected: actions.Ui.ConfigDoc.selectSidebarViewMode
}

type SidebarProps = ReturnType<typeof mapStateToProps>;

class Sidebar extends React.PureComponent<SidebarProps> {
    public render(): JSX.Element {
        return (<AppSidebarWithMappedViews {...this.props}/>);
    }
}

// tslint:disable-next-line:max-classes-per-file
class AppSidebarWithMappedViews extends AppSidebar {

    protected renderDefaultView(): JSX.Element {
        return (<SidebarDefaultView />);
    }

}

export default connect(mapStateToProps, mapDispatchToProps)(Sidebar);
