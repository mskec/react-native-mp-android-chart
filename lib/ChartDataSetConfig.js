import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';


const chartDataSetConfig = {
  common: {
    color: PropTypes.string,
    colors: PropTypes.arrayOf(PropTypes.string),

    drawValues: PropTypes.bool
  },

  barLineScatterCandleBubble: {
    highlightColor: PropTypes.string
  },

  lineScatterCandleRadar: {
    drawHighlightIndicators: PropTypes.bool,
    drawVerticalHighlightIndicator: PropTypes.bool,
    drawHorizontalHighlightIndicator: PropTypes.bool,
    highlightLineWidth: PropTypes.number
  },

  lineRadar: {
    fillColor: PropTypes.string,
    fillAlpha: PropTypes.number,
    drawFilled: PropTypes.bool,
    lineWidth: (props, propName, componentName) => {
      let lineWidth = props[propName];
      if (typeof lineWidth !== 'Number' && lineWidth < 0.2 || lineWidth > 10) {
        return new Error(
          `Invalid prop ${propName} supplied to '${componentName}'. Value must be number and between 0.2f and 10f`
        );
      }
    }
  }
};

export default chartDataSetConfig;
