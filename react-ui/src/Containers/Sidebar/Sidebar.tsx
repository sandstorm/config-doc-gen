import * as React from 'react';
import {connect} from 'react-redux';

import {actions, selectors} from '../../Redux/Store';

import AppSidebar from "../../Components/Organisms/AppSidebar/AppSidebar";
import { IApplicationState } from '../../Redux/Store';
import SidebarDefaultView from '../SidebarDefaultView';

const mapStateToProps = (state: IApplicationState) => ({
    viewMode: selectors.Ui.ConfigDoc.sidebarViewMode(state),
});

const mapDispatchToProps = {
    onViewModeSelected: actions.Ui.ConfigDoc.selectSidebarViewMode
}

type SidebarProps = ReturnType<typeof mapStateToProps> & typeof mapDispatchToProps;

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
