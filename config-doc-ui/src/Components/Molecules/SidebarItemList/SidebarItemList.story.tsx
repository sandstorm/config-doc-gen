import {storiesOf} from '@storybook/react';
import * as React from 'react';
import SidebarItemList from '.';
import {UiItemType} from "../../../Domain/Ui/UiItem";

storiesOf('SidebarItemList', module)
    .add('default', () => (
        <SidebarItemList/>
    ))
    .add('with properties prop', () => (
        <SidebarItemList items={[
            {
                identifier: "foo.prop1",
                label: "prop1",
                type: UiItemType.PROPERTY_API
            },
            {
                identifier: "foo.prop2",
                label: "prop2",
                type: UiItemType.PROPERTY_IMPLEMENTATION
            },
            {
                identifier: "foo.bar.namespace",
                label: "foo.bar.namespace",
                type: UiItemType.NAMESPACE
            },
            {
                identifier: "foo.unknown",
                label: "unknown type",
                type: UiItemType.UNKNOWN
            }
        ]}/>
    ));
