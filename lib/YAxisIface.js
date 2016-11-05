import {PropTypes} from 'react';
import {
  View
} from 'react-native';

import {axisIface} from './ChartBase';

const yAxisIface = {
  ...axisIface,

  axisMaxValue: PropTypes.number,
  axisMinValue: PropTypes.number,
  inverted: PropTypes.bool,
  spaceTop: PropTypes.number,
  spaceBottom: PropTypes.number,
  showOnlyMinMax: PropTypes.bool,
  labelCount: PropTypes.number,
  labelCountForce: PropTypes.bool,
  position: PropTypes.string,
  granularity: PropTypes.number,
  granularityEnabled: PropTypes.bool,

  // formatting
  valueFormatter: PropTypes.string,

  // zero line
  zeroLine: PropTypes.shape({
    enabled: PropTypes.bool,
    lineWidth: PropTypes.number,
    lineColor: PropTypes.string
  })
};

export default yAxisIface;
