import * as React from 'react';

import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {iconForSidebarViewMode} from "../../../icons";

import {UiItem} from "../../../Domain/Ui/UiItem";

import {allSidebarViewModes, UiSidebarViewMode} from "../../../Redux/Store/Ui/UiStore";

import AppSidebarDefaultView from "../AppSidebarDefaultView/AppSidebarDefaultView";

//
// Props
//
interface IAppSidebarProps {
    readonly selectedItem: UiItem | null;
    readonly viewMode: UiSidebarViewMode;

    // actions
    readonly onViewModeSelected: (viewMode: UiSidebarViewMode) => void;
}

type DefaultProps = Readonly<Required<{
    selectedItem: UiItem | null;
    viewMode: UiSidebarViewMode;

    onViewModeSelected: (viewMode: UiSidebarViewMode) => void;
}>>;

const defaultProps: DefaultProps = {
    selectedItem: null,
    viewMode: UiSidebarViewMode.DEFAULT,

    // no ops actions
    onViewModeSelected: () => {return;}
};

//
// Class
//
export default class AppSidebar extends React.PureComponent<IAppSidebarProps, any> {
    public static readonly defaultProps = defaultProps;

    public render() {
        return <div className="app-sidebar">
            <div className="app-sidebar--header">
                Sidebar title ...
            </div>
            <div className="app-sidebar--view-mode">
                {allSidebarViewModes().map(viewMode => {
                    const clickHandler = () => this.props.onViewModeSelected(viewMode);
                    return <div
                        title={this.renderViewModeLabel(viewMode)} 
                        key={viewMode} 
                        onClick={clickHandler} 
                        className={"tab" + (viewMode === this.props.viewMode ? " current" : "")}
                        >
                        <FontAwesomeIcon icon={iconForSidebarViewMode(viewMode)}/>
                    </div>
                })}
            </div>
            <div className="app-sidebar--content">
                <div className={"app-sidebar-content--" + this.viewModeAsLowerCasedName() + "-view"}>
                    {this.renderView()}
                </div>
            </div>
        </div>;
    }

    protected renderDefaultView(): JSX.Element {
        return (<AppSidebarDefaultView/>);
    }

    protected renderFlatView(): JSX.Element {
        return (<span>flat view</span>);
    }

    protected renderTreeView(): JSX.Element {
        return (<span>tree view</span>);
    }

    protected renderGroupedView(): JSX.Element {
        return (<span>grouped view</span>);
    }

    private renderView(): JSX.Element {
        switch (this.props.viewMode) {
            case UiSidebarViewMode.FLAT:
                return this.renderFlatView();
            case UiSidebarViewMode.TREE:
                return this.renderTreeView();
            case UiSidebarViewMode.GROUPED:
                return this.renderGroupedView();
            case UiSidebarViewMode.DEFAULT:
            default:
                return this.renderDefaultView();
        }
    }

    private renderViewModeLabel(viewMode: UiSidebarViewMode): string {
        // TODO readable label
        return UiSidebarViewMode[viewMode].toLowerCase();
    }

    private viewModeAsLowerCasedName(): string {
        return UiSidebarViewMode[this.props.viewMode ? this.props.viewMode : UiSidebarViewMode.DEFAULT].toLowerCase();
    }

}
