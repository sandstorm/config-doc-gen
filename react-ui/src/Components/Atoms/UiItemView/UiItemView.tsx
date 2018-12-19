import * as React from 'react';

import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {iconForItem} from "../../../icons";

import {UiItem, UiItemType} from "../../../Domain/Ui/UiItem";

//
// Props
//
interface IUiItemViewProps {
  readonly item: UiItem;
}

type DefaultProps = Readonly<Required<{
  item: UiItem;
}>>;

const defaultProps: DefaultProps = {
  item: {
    identifier: "foo-item",
    label: "Foo Item",
    type: UiItemType.UNKNOWN
  },
};

//
// Class
//
export default class UiItemView extends React.PureComponent<IUiItemViewProps, any> {
  public static readonly defaultProps = defaultProps;

  public render(): JSX.Element {
    return (
      <div className="ui-item-view">
        <FontAwesomeIcon icon={iconForItem(this.props.item.type)}/>
        <span>{this.props.item.label}</span>
      </div>
    );
  }

}
