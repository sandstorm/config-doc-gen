import * as React from 'react';

import {UiItem} from "../../../Domain/Ui/UiItem";

import SidebarItemList from "../../Molecules/SidebarItemList/SidebarItemList";

//
// Props
//
interface IAppSidebarDefaultViewProps {
    readonly namespaces: ReadonlyArray<UiItem>;
    readonly properties: ReadonlyArray<UiItem>;
    readonly selectedNamespace: UiItem | null;
    readonly selectedProperty: UiItem | null;

    readonly onSelectNamespace: (item: UiItem) => void;
    readonly onSelectProperty: (item: UiItem) => void;
}

type DefaultProps = Readonly<Required<{
    namespaces: ReadonlyArray<UiItem>;
    properties: ReadonlyArray<UiItem>;
    selectedNamespace: UiItem | null;
    selectedProperty: UiItem | null;

    onSelectNamespace: (item: UiItem) => void;
    onSelectProperty: (item: UiItem) => void;
}>>;

const defaultProps: DefaultProps = {
    namespaces: [],
    properties: [],
    selectedNamespace: null,
    selectedProperty: null,

    onSelectNamespace: () => {return},
    onSelectProperty: () => {return},
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
                    <SidebarItemList items={this.props.namespaces} selectedItem={this.props.selectedNamespace} onClickItem={this.props.onSelectNamespace}/>
                </div>
                <div className="sidebar-default-view--properties">
                    <SidebarItemList items={this.props.properties} selectedItem={this.props.selectedProperty} onClickItem={this.props.onSelectProperty}/>
                </div>
            </div>
        );
    }
}
