import * as React from 'react';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { noPropertiesIcon } from "../../../icons";

import { UiItem, UiItemType } from "../../../Domain/Ui/UiItem";

import UiItemView from '../../Atoms/UiItemView';

//
// Props
//
interface ISidebarPropertyListProps {
  readonly items: ReadonlyArray<UiItem>;
  readonly selectedItem: UiItem | null;
  readonly onClickItem: (item: UiItem) => void;
}

type DefaultProps = Readonly<Required<{
  items: ReadonlyArray<UiItem>;
  selectedItem: UiItem | null;
  readonly onClickItem: (item: UiItem) => void;
}>>;

const defaultProps: DefaultProps = {
  items: [],
  onClickItem: () => { return },
  selectedItem: null,
};

//
// Class
//
export default class SidebarItemList extends React.PureComponent<ISidebarPropertyListProps, any> {
  public static readonly defaultProps = defaultProps;

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
        {this.props.items.map(item => {
          const clickHandler = () => this.props.onClickItem(item);
          return <li
            className={"sidebar-item-list--" + this.buildItemClassPrefix(item.type) + (item === this.props.selectedItem ? " selected" : "")}
            key={item.identifier}
            onClick={clickHandler}
          >
            <UiItemView item={item} />
          </li>
        })}
      </ul>
    );
  }

  private buildItemClassPrefix(type: UiItemType): string {
    return UiItemType[type].toLowerCase();
  }

  private renderEmptyNotification(): JSX.Element {
    return (
      <div className="sidebar-item-list--empty">
        <FontAwesomeIcon icon={noPropertiesIcon} />
        <span>No List Items!</span>
      </div>
    );
  }

}
