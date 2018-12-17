import * as React from 'react';
import './_SidebarItemList.scss';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {iconForItem, noPropertiesIcon} from "../../../icons";
import {UiItem, UiItemType} from "../../../Domain/Ui/UiItem";

//
// Props
//
interface SidebarPropertyListProps {
    readonly items: ReadonlyArray<UiItem>;
    //readonly showQualifiedName: boolean;
    //readonly onClickItem: (itemName: string) => void
}

type DefaultProps = Readonly<Required<{
    items: ReadonlyArray<UiItem>;
}>>;

const defaultProps: DefaultProps = {
    items: [],
};

//
// State
//
interface SidebarPropertyListState {
}

const initialTemplateState: SidebarPropertyListState = {};

//
// Class
//
export default class SidebarItemList extends React.PureComponent<SidebarPropertyListProps, SidebarPropertyListState> {
    public static readonly defaultProps = defaultProps;

    public constructor(props: SidebarPropertyListProps) {
        super(props);
        this.state = initialTemplateState;
    }

    public render(): JSX.Element {
        return (
            <div className="sidebar-item-list">
                {
                    this.props.items.length > 0 ?
                        this.renderList() : this.renderEmptyNotification()
                }

            </div>
        );
    }

    private renderList(): JSX.Element {
        return (
            <ul>
                {this.props.items.map(item =>
                    <li
                        className={"sidebar-item-list--" + this.buildItemClassPrefix(item.type)}
                        key={item.identifier}
                        onClick={() => /*onClickItem(property.qualifiedName)*/ ""}
                    >
                        <FontAwesomeIcon icon={iconForItem(item.type)}/>
                        {item.label}
                    </li>
                )}
            </ul>
        );
    }

    private buildItemClassPrefix(type: UiItemType): string {
        return UiItemType[type].toLowerCase();
    }

    private renderEmptyNotification(): JSX.Element {
        return (
            <div className="sidebar-item-list--empty">
                <FontAwesomeIcon icon={noPropertiesIcon}/>
                No Properties!
            </div>
        );
    }

}
