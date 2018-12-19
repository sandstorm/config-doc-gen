import * as React from 'react';

import {UiItem} from "../../../Domain/Ui/UiItem";

import SidebarItemList from "../../Molecules/SidebarItemList/SidebarItemList";

//
// Props
//
interface IAppSidebarDefaultViewProps {
    readonly namespaces: ReadonlyArray<UiItem>;
    readonly properties: ReadonlyArray<UiItem>;
    readonly selectedItem: UiItem | null;

    readonly onSelectItem: (item: UiItem) => void;
}

type DefaultProps = Readonly<Required<{
    namespaces: ReadonlyArray<UiItem>;
    properties: ReadonlyArray<UiItem>;
    selectedItem: UiItem | null;

    onSelectItem: (item: UiItem) => void;
}>>;

const defaultProps: DefaultProps = {
    namespaces: [],
    properties: [],
    selectedItem: null,

    onSelectItem: () => {return}
};

//
// Class
//
export default class AppSidebarDefaultView extends React.PureComponent<IAppSidebarDefaultViewProps, any> {
    public static readonly defaultProps = defaultProps;

    public render() {
        return (
            <div className="sidebar-default-view">
                <div className="sidebar-default-view--namespaces">
                    <SidebarItemList items={this.props.namespaces} selectedItem={this.props.selectedItem} onClickItem={this.props.onSelectItem}/>
                </div>
                <div className="sidebar-default-view--properties">
                    <SidebarItemList items={this.props.properties} selectedItem={this.props.selectedItem} onClickItem={this.props.onSelectItem}/>
                </div>
            </div>
        );
    }
}
