import * as React from 'react';
import { render } from 'react-dom';
import * as ReactTestRenderer from 'react-test-renderer';
import UiItemView from '.';

describe('SidebarPropertyList tests', () => {
    it('should render without crashing', () => {
        render(<UiItemView />, document.createElement('div'));
    });

    it('should match snapshot', () => {
        expect(ReactTestRenderer.create(<UiItemView />)).toMatchSnapshot();
    });
});
