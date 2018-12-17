import * as React from 'react';
//import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import './_AppSidebar.scss';
import {UiSidebarViewMode} from "../../../Redux/Store/Ui/UiStore";
import {UiItem} from "../../../Domain/Ui/UiItem";


//
// Props
//
interface AppSidebarProps {
    readonly viewMode: UiSidebarViewMode;
    readonly selectedItem: UiItem | null;
}

type DefaultProps = Readonly<Required<{
    viewMode: UiSidebarViewMode,
    selectedItem: UiItem | null;
}>>;

const defaultProps: DefaultProps = {
    viewMode: UiSidebarViewMode.FLAT,
    selectedItem: null
};

//
// State
//
interface AppSidebarState {
}

const initialAppSidebarState: AppSidebarState = {};

//
// Class
//
export default class AppSidebar extends React.PureComponent<AppSidebarProps, AppSidebarState> {
    public static readonly defaultProps = defaultProps;

    public constructor(props: AppSidebarProps = defaultProps) {
        super(props);
        this.state = initialAppSidebarState;
    }

    render() {
        return (
            <div className="sidebar">
                <div className="sidebar--view-mode">
                    Sidebar: {UiSidebarViewMode[this.props.viewMode]}
                </div>
                <div className="sidebar--content">
                    Sidebar Content
                </div>
            </div>
        );
    }
}
