import * as React from 'react';
import {render} from 'react-dom';
import * as ReactTestRenderer from 'react-test-renderer';
import SidebarPropertyList from '.';
import {UiItemType} from "../../../Domain/Ui/UiItem";

describe('SidebarPropertyList tests', () => {
    it('should render without crashing', () => {
        render(<SidebarPropertyList/>, document.createElement('div'));
    });

    it('should match snapshot', () => {
        expect(ReactTestRenderer.create(<SidebarPropertyList/>)).toMatchSnapshot();
    });

    it('should match snapshot with template props', () => {
        expect(ReactTestRenderer.create(<SidebarPropertyList items={[
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
        ]}/>)).toMatchSnapshot();
    });
});
